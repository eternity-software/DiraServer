package ru.etysoft.dira.requests.handlers;

import ru.etysoft.dira.requests.encryption.KeyRenewRequest;
import ru.etysoft.dira.requests.entities.BaseMember;
import ru.etysoft.dira.sockets.ClientHandlerContract;
import ru.etysoft.dira.sockets.MasterSocketContract;
import ru.etysoft.dira.updates.DhInitUpdate;
import ru.etysoft.dira.updates.RenewingCancelUpdate;
import ru.etysoft.dira.updates.RenewingConfirmUpdate;
import ru.etysoft.dira.utils.Logger;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class KeyRenewHandler extends RequestHandler {

    public static final int KEY_BIT_LENGTH = 2048;
    private static final HashMap<String, List<BaseMember>> renewingMembers = new HashMap<>();
    private static final HashMap<String, List<BaseMember>> renewedMembers = new HashMap<>();

    public KeyRenewHandler(KeyRenewRequest request, ClientHandlerContract clientHandlerContract, MasterSocketContract masterSocketContract) {
        super(request, clientHandlerContract, masterSocketContract);
    }

    public static void addConfirmedMember(String roomSecret, BaseMember baseMember) {
        List<BaseMember> baseMembers = new ArrayList<>();

        if (renewedMembers.containsKey(roomSecret)) {
            baseMembers = renewedMembers.get(roomSecret);
        }


        for (BaseMember bM : baseMembers) {
            if (baseMember.getId().equals(bM.getId())) return;
        }
        baseMembers.add(baseMember);

        renewedMembers.put(roomSecret, baseMembers);
    }

    public static boolean checkRenewingStatus(String roomSecret, RequestHandler requestHandler) {
        if (renewingMembers.get(roomSecret) == null | renewedMembers.get(roomSecret) == null) return false;

        if (renewedMembers.get(roomSecret).size() == 0) return false;

        for (String memberId : IntermediateKeyHandler.updateCounter.get(roomSecret).keySet()) {
            int count = IntermediateKeyHandler.updateCounter.get(roomSecret).get(memberId);

            if (count > renewingMembers.get(roomSecret).size()) {
                Logger.warning("(DH) Incorrect requests count -> count=" + count + " > renewingCount=" + renewingMembers.get(roomSecret).size() + " id=" + memberId, "KeyRenewHandler");
                return false;
            }
        }

        if (renewingMembers.get(roomSecret).size() == renewedMembers.get(roomSecret).size()) {
            requestHandler.getUpdatesPool(roomSecret).registerUpdate(
                    new RenewingConfirmUpdate(System.currentTimeMillis()).setRoomSecret(roomSecret));
            renewedMembers.remove(roomSecret);
            renewingMembers.remove(roomSecret);
            IntermediateKeyHandler.updateCounter.remove(roomSecret);
            return true;
        }

        return false;
    }

    public static List<BaseMember> clearBaseMembers(String roomSecret) {
        return renewingMembers.remove(roomSecret);
    }

    public static List<BaseMember> getBaseMembers(String roomSecret) {
        return renewingMembers.get(roomSecret);
    }

    @Override
    public void process() {
        KeyRenewRequest keyRenewRequest = (KeyRenewRequest) getRequest();

        if (renewingMembers.containsKey(keyRenewRequest.getRoomSecret())) {
            sendRequestAcceptedStatus(false);
            return;
        }


        BigInteger G = BigInteger.probablePrime(KEY_BIT_LENGTH, new SecureRandom());
        BigInteger P = BigInteger.probablePrime(KEY_BIT_LENGTH, new SecureRandom());

        List<BaseMember> memberList = new ArrayList<>(PingReactHandler.getBaseMembers(keyRenewRequest.getRoomSecret()));
        PingReactHandler.clearBaseMembers(keyRenewRequest.getRoomSecret());
        renewingMembers.put(keyRenewRequest.getRoomSecret(), memberList);

        DhInitUpdate dhInitUpdate = new DhInitUpdate(memberList, G.toString(), P.toString());

        getUpdatesPool(keyRenewRequest.getRoomSecret()).registerUpdate(
                dhInitUpdate.setRoomSecret(keyRenewRequest.getRoomSecret()));
        sendRequestAcceptedStatus(true);
        Thread renewWatcherThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(30000);
                    if (renewingMembers.get(keyRenewRequest.getRoomSecret()).size() != 0) {
                        if (!checkRenewingStatus(keyRenewRequest.getRoomSecret(), KeyRenewHandler.this)) {
                            getUpdatesPool(keyRenewRequest.getRoomSecret()).registerUpdate(
                                    new RenewingCancelUpdate().setRoomSecret(keyRenewRequest.getRoomSecret()));
                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
                renewedMembers.remove(keyRenewRequest.getRoomSecret());
                renewingMembers.remove(keyRenewRequest.getRoomSecret());
                IntermediateKeyHandler.updateCounter.remove(keyRenewRequest.getRoomSecret());
            }
        });
        renewWatcherThread.start();
    }

}