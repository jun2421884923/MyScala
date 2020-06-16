package com.operating;

import org.apache.commons.io.IOUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;

public class HdfsUtil {
    public static void main(String[] args) throws IOException {
        FileSystem fs=null;
        String uri=args[0];//从键盘输入路径参数
        System.out.println("uri=="+uri);
        Configuration conf = new Configuration();
        conf.set("fs.defaultFS", "hdfs://0");
        conf.set("fs.hdfs.impl", "org.apache.hadoop.hdfs.DistributedFileSystem");
        try {
            fs = FileSystem.get(new URI(uri),conf);
            Path dfs = new Path(uri);
            fs.mkdirs(dfs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if (fs!=null){
                fs.close();
            }

        }
    }
}


//hdfs://ng775faa5-master-instance-izdqstvm.novalocal:8020/home/video/jun.chang/test