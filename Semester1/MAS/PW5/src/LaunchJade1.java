

import jade.core.Runtime;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.wrapper.*;
import my_try.Searcher;

public class LaunchJade1 {
    private Runtime rt;
    private static AgentContainer mc;

    public LaunchJade1() {
        // Lancement de la plateforme Jade
        try {

            // Get a hold on JADE runtime
            rt = Runtime.instance();

            // Exit the JVM when there are no more containers around
            rt.setCloseVM(true);


            // Launch a complete platform on the 8888 port
            // create a default Profile
            Profile pMain = new ProfileImpl(null, 8888, null);

            System.out.println("Launching a whole in-process platform..." + pMain);
            mc = rt.createMainContainer(pMain);

        /* set now the default Profile to start a container
        ProfileImpl pContainer = new ProfileImpl(null, 8888, null);
        System.out.println("Launching the agent container ..."+pContainer);
        AgentContainer cont = rt.createAgentContainer(pContainer);
        System.out.println("Launching the agent container after ..."+pContainer);*/


        } // fin du try
        catch (Exception e) {
            e.printStackTrace();
        }

        try {

            AgentController serviceAgent = mc.createNewAgent("Service-Agent", my_try.Service.class.getName(), new Object[]{});
            serviceAgent.start();
            AgentController serviceAgent2 = mc.createNewAgent("Service-Agent2", my_try.Service.class.getName(), new Object[]{});
            serviceAgent2.start();
            AgentController searcherAgent = mc.createNewAgent("Sercher-Agent", my_try.Searcher.class.getName(), new Object[]{});
            searcherAgent.start();

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}