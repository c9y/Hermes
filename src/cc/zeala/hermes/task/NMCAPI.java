package cc.zeala.hermes.task;

import org.bukkit.craftbukkit.libs.org.ibex.nestedvm.util.Seekable;
import org.bukkit.entity.Player;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class NMCAPI {

    public boolean voter(Player player) {
        boolean voter = false;
        try {
            URL url = new URL("https://api.namemc.com/server/koven.cc/likes?profile=" + player.getUniqueId().toString());
            URLConnection urlConnection = url.openConnection();
            InputStream inputStream = urlConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                voter = Boolean.parseBoolean(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return voter;
    }

}
