package sem.group5.bob.client.mappGenerator;

import java.io.IOException;

/**
 * todo
 */
public class ExecutableLauncher {


    /**
     * todo
     * @throws IOException
     */
    private void OSDiscoverer() throws IOException
    {
        String OS = System.getProperty("os.name").toLowerCase();
        if (OS.contains("win"))
        {
            System.out.println("os is windows");
            Runtime.getRuntime().exec(new String[]{"cmd", "/c","start chrome http://tinyurl.com/6fhuqvk"});
        } else if(OS.contains("mac"))
        {
            System.out.println("os is mac");
            String cmd = "open -a Safari http://tinyurl.com/6fhuqvk";
            Process p = Runtime.getRuntime().exec(cmd);
        }else if(OS.contains("unix"))
        {
            System.out.println("os is linux");
            Runtime.getRuntime().exec(new String[] {"google-chrome", "http://tinyurl.com/6fhuqvk/"});
        }

    }

    /**
     * todo
     * @param args todo
     * @throws IOException
     */
    public static void main(String[] args) throws IOException
    {
        ExecutableLauncher ex = new ExecutableLauncher();
        ex.OSDiscoverer();

    }

}