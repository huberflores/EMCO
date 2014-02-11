package mc.ut.ee.emco.network;

public class Main {


    public static void main(String[] args) {
        NetworkManagerServer nm = new NetworkManagerServer(NetInfo.port);
        nm.makeconnection();
    }

}