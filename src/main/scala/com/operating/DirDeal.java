package com.operating;

import java.io.*;

public class DirDeal {
    /**
     *  根据路径删除指定的目录或文件，无论存在与否
     *@param sPath  要删除的目录或文件
     *@return 删除成功返回 true，否则返回 false。
     */
    public static boolean DeleteFolder(String sPath) {
        boolean flag = false;
        File file = new File(sPath);
        // 判断目录或文件是否存在
        if (!file.exists()) {  // 不存在返回 false
            return !flag;
        } else {
            // 判断是否为文件
            if (file.isFile()) {  // 为文件时调用删除文件方法
                return deleteFile(sPath);
            } else {  // 为目录时调用删除目录方法
                return deleteDirectory(sPath);
            }
        }
    }
    /**
     * 删除单个文件
     * @param   sPath    被删除文件的文件名
     * @return 单个文件删除成功返回true，否则返回false
     */
    public static boolean deleteFile(String sPath) {
        boolean flag = false;
        File file = new File(sPath);
        // 路径为文件且不为空则进行删除
        if (file.isFile() && file.exists()) {
            file.delete();
            flag = true;
        }
        return flag;
    }
    /**
     * 删除目录（文件夹）以及目录下的文件
     * @param   sPath 被删除目录的文件路径
     * @return  目录删除成功返回true，否则返回false
     */
    public  static boolean deleteDirectory(String sPath) {
        //如果sPath不以文件分隔符结尾，自动添加文件分隔符
        if (!sPath.endsWith(File.separator)) {
            sPath = sPath + File.separator;
        }
        File dirFile = new File(sPath);
        //如果dir对应的文件不存在，或者不是一个目录，则退出
        if (!dirFile.exists() || !dirFile.isDirectory()) {
            return false;
        }
        boolean flag = true;
        //删除文件夹下的所有文件(包括子目录)
        File[] files = dirFile.listFiles();
        for (int i = 0; i < files.length; i++) {
            //删除子文件
            if (files[i].isFile()) {
                flag = deleteFile(files[i].getAbsolutePath());
                if (!flag) {
                    break;
                }
            } //删除子目录
            else {
                flag = deleteDirectory(files[i].getAbsolutePath());
                if (!flag) {
                    break;
                }
            }
        }
        if (!flag) {
            return false;
        }
        //删除当前目录
        if (dirFile.delete()) {
            return true;
        } else {
            return false;
        }
    }


    /**
     * 复制多级文件夹
     * @param src
     * @param target
     * @throws IOException
     */
    public  static  void CopyDirs(String src, String target) throws IOException {
        System.out.println("目录"+src+"=====>"+target);
        File srcfile=new File(src);
        File targetfile =new File(target);
        if(!targetfile.exists()){
            System.out.println("目标文件夹不存在，创建"+targetfile.getAbsolutePath());
            targetfile.mkdirs();
        }
        for(File sf:srcfile.listFiles()){
            if(sf.isDirectory()){
                System.out.println(sf.getAbsolutePath()+"是目录");
                String name =sf.getName();
                File tarfile=new File(target,name);

                CopyDirs(sf.getAbsolutePath(),tarfile.getAbsolutePath());
            }else{
                System.out.println(sf.getAbsolutePath()+"是文件，直接复制");
                String name =sf.getName();
                File tarfile=new File(target,name);
                copyfile(sf,tarfile);
            }
        }
    }
    /**
     * 复制文件  bufferedinputstream
     * @param sf
     * @param tf
     * @throws IOException
     */
    private static void copyfile(File sf, File tf) throws IOException {
        System.out.println(sf+"--->"+tf);
        BufferedInputStream bis=new BufferedInputStream(new FileInputStream(sf));
        BufferedOutputStream bos=new BufferedOutputStream(new FileOutputStream(tf));
        byte[] bys=new byte[1024];
        int len=0;
        while ((len=bis.read(bys))!=-1){
            bos.write(bys,0,len);
        }
        bos.close();
        bis.close();

    }
}
