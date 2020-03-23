package services;

import entities.Good;
import util.SovaBooksLoadUtil;

public class DownLoadService {
    public static Good loadByUrl(String url) {
        if (url.contains("sovabooks.com.ua")) {
            return SovaBooksLoadUtil.loadGoodByURL(url);
        } else if(false){

        } else {
            throw new IllegalArgumentException("unknown site for loading");
        }
        return null;
    }
}
