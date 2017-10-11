package vn.sogo.lmscms.common;

import org.apache.log4j.Logger;

public class DownloadEventFlag {

    private static Logger log = Logger.getLogger(DownloadEventFlag.class);

    public int CYCLE = 3;

    public long timeOut = 1000;

    int m_flag = 0; /*the bit string flag */

    public DownloadEventFlag() {
    }

    public synchronized int get(int flag) {

        /** wait until bits match */

        while (m_flag != flag) {
            try {
                wait();
            } catch (InterruptedException e) {

                /** thread was interrupted */
                return 0;
            }
        }

        /** return the current unmasked flag value */
        return m_flag;
    }

    public synchronized int get(int flag, long timeOut) {

        /** wait until bits match */
        while (m_flag != flag) {
            if (m_flag > this.CYCLE) {
                return m_flag;
            }
            try {
                if (timeOut > 0) {
                    wait(timeOut);
                } else {
                    wait();
                }
            } catch (InterruptedException e) {
                /** thread was interrupted */
                return 0;
            }
        }

        return m_flag;
    }

    public synchronized int set(int flag) {
        if (m_flag < CYCLE) {
            if (flag > CYCLE) {
                m_flag = flag;
            } else {
                m_flag = flag % CYCLE;
            }
        }

        log.info("Some one set flag to " + m_flag
                + " by original value " + flag);

        notifyAll();

        Thread.yield();
        return m_flag;
    }
}
