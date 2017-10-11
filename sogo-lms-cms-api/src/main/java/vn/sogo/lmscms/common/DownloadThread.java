package vn.sogo.lmscms.common;


import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class DownloadThread implements Runnable {

    public boolean useHeader = true;

    public boolean useFooter = false;

    public OutputStreamWriter out = null;

    protected HttpServletRequest request;

    protected HttpServletResponse response;

    protected int threadId = -1;

    protected int maxThread = 0;

    private DownloadEventFlag downloadEventFlag = null;

    private boolean endThread = false;

    private int countExecuting = 1;


     public DownloadThread(HttpServletRequest request,
            HttpServletResponse response, DownloadEventFlag downloadEventFlag,
            int threadId, int maxThread) {
        this.request = request;
        this.response = response;
        this.threadId = threadId;
        this.downloadEventFlag = downloadEventFlag;
        this.maxThread = maxThread;
    }


    public void run() {
        if (!initDownloadProcess()) {

            /* send signal to other thread to stop download. */
            downloadEventFlag.set(maxThread + 1);
            return;
        }
        if ((request == null) || (response == null)) {
            return;
        }
        try {

            char[] buf = null;
            int count = threadId;
                while (count < countExecuting) {
                    /* Get datas. */
                    List datas = getDatas(count);

                    /* Wait for other threads to write data. */
                    int flag = downloadEventFlag
                                            .get(threadId, maxThread * 1000);

                    /* if flag is set. */
                    if (flag == threadId) {
                        try {

                        /* when count == 0, we write header if needs. */
                        if (count == 0) {
                            if (useHeader) {
                                String header = getFileHeader();
                                buf = header.toCharArray();
                                if (buf != null && buf.length >= 0) {
                                    out.write(buf);
                                }
                            }
                        }

                        /* write data. */
                        if ((datas != null) && (datas.size() != 0)) {
                            for (int i = 0; i < datas.size(); i++) {
                                Object data = datas.get(i);
                                String line = formatData(data);
                                buf = line.toCharArray();
                                if (buf != null && buf.length >= 0) {
                                        out.write(buf);
                                }
                             }
                            out.flush();
                        }

                        /* return the control to other threads. */
                        if (isEndThread() && (count >= (countExecuting - 1))) {
                            downloadEventFlag.set(maxThread + 1);
                        } else {
                            downloadEventFlag.set(threadId + 1);
                        }

                        /* setting the next value segment that will be read.*/
                        count += maxThread;
                        } catch (IOException e) {
                            downloadEventFlag.set(maxThread + 1);
                        }
                    } else {
                        downloadEventFlag.set(maxThread + 1);
                        break;
                    }
                }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void setCountExecuting(int countExecuting) {
        this.countExecuting = countExecuting;
    }

    public abstract boolean initDownloadProcess();

    public abstract List getDatas(int countParam) throws Exception;

    public abstract String formatData(Object data) throws Exception;

    public abstract String getFileHeader() throws Exception;

    public abstract String getFileFooter();

    public boolean isEndThread() {
        return endThread;
    }

    public void setEndThread(boolean determination) {
        endThread = determination;
    }

    public int getCountExecuting() {
        return countExecuting;
    }

}
