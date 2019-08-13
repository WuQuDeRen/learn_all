package com.learn.concurrent;

import java.io.FileWriter;
import java.io.IOException;

/**
 * @desc
 * 四个线程t1, t2, t3, t4, 向4个文件中写入数据，t1只能写入1，t2只能写入2，t3只能写入3，t4只能写入4，对4个文件A，B，C，D写入如下内容:
 * A:123412341234.....
 * B:234123412341....
 * C:341234123412....
 * D:412341234123....
 * 怎么实现同步可以让线程并行工作？
 * @author ji_fei
 * @date  2019-07-16 13:59
 */
public class PrintFileThread {
    private static Object aLock = new Object();
    private static Object bLock = new Object();
    private static Object cLock = new Object();
    private static Object dLock = new Object();

    public static boolean aFlag = false;
    public static boolean bFlag = false;
    public static boolean cFlag = false;
    public static boolean dFlag = false;



    public static void main(String[] args) throws IOException, InterruptedException {
        FileWriter aFile = new FileWriter("/Users/ji_fei/Desktop/thread/a.txt", true);
        FileWriter bFile = new FileWriter("/Users/ji_fei/Desktop/thread/b.txt", true);
        FileWriter cFile = new FileWriter("/Users/ji_fei/Desktop/thread/c.txt", true);
        FileWriter dFile = new FileWriter("/Users/ji_fei/Desktop/thread/d.txt", true);
        Thread aThread = new Thread(() -> {
           while(true) {
                synchronized (dLock) {
                    synchronized (aLock) {
                        try {
                            aFile.write("1");
                            aFile.flush();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        aFlag = true;

                        if (bFlag) {
                            try {
                                bFile.write("1");
                                bFile.flush();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }

                        if (cFlag) {
                            try {
                                cFile.write("1");
                                cFile.flush();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }

                        if (dFlag) {
                            try {
                                dFile.write("1");
                                dFile.flush();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }

                        aLock.notify();

                    }
                    try {
                        dLock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
           }
        });
        Thread bThread = new Thread(() -> {
            while(true) {
                synchronized (aLock) {
                    synchronized (bLock) {

                        if (aFlag) {
                            try {
                                aFile.write("2");
                                aFile.flush();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }

                        try {
                            bFile.write("2");
                            bFile.flush();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        bFlag = true;
                        if (cFlag) {
                            try {
                                cFile.write("2");
                                cFile.flush();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        if (dFlag) {
                            try {
                                dFile.write("2");
                                dFile.flush();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }

                        bLock.notify();
                    }
                    try {
                        aLock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        Thread cThread = new Thread(() -> {
            while(true) {
                synchronized (bLock) {
                    synchronized (cLock) {
                        if (aFlag) {
                            try {
                                aFile.write("3");
                                aFile.flush();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        if (bFlag) {
                            try {
                                bFile.write("3");
                                bFile.flush();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }

                        try {
                            cFile.write("3");
                            cFile.flush();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        cFlag = true;
                        if (dFlag) {
                            try {
                                dFile.write("3");
                                dFile.flush();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }

                        cLock.notify();
                    }
                    try {
                        bLock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        Thread dThread = new Thread(() -> {
            while(true) {
                synchronized (cLock) {
                    synchronized (dLock) {
                        if (aFlag) {
                            try {
                                aFile.write("4");
                                aFile.flush();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        if (bFlag) {
                            try {
                                bFile.write("4");
                                bFile.flush();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        if (cFlag) {
                            try {
                                cFile.write("4");
                                cFile.flush();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }

                        try {
                            dFile.write("4");
                            dFile.flush();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        dFlag = true;

                        dLock.notify();
                    }
                    try {
                        cLock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        aThread.start();
        Thread.sleep(1000);
        bThread.start();

        cThread.start();

        dThread.start();


    }



}
