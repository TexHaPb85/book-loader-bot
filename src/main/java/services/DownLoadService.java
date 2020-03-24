package services;

import entities.Good;

public interface DownLoadService {

    static Good loadByUrl(String url) {
        DownLoadService downLoadService;
        if (url.contains("sovabooks")) {
            downLoadService = SovaBookDownloadService.getInstance();
        } else {
            throw new IllegalArgumentException("unknown site for loading");
        }
        return downLoadService.loadGoodByUrl(url);
    }

    Good loadGoodByUrl(String url);
}
