package com.learn.hanjx.util.io;

import com.learn.hanjx.exceptionAndError.exception.HanException;
import com.learn.hanjx.util.HanHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 * 一些关于文件的操作函数。
 */
public class FileUtil
{
    static private final Logger LOG = LoggerFactory.getLogger(FileUtil.class);

    /**
     * 从文件中把数据直接读取出来。
     *
     * @param fileName
     * @return
     */
    public static byte[] loadDataFromFile(String fileName)
    {
        byte[] data;

        File file = new File(fileName);

        if (!file.exists())
            return null;
        // 最大只能读取50MB的文件。
        if (file.length() > 1024 * 1024 * 50)
            return null;

        data = new byte[(int) file.length()];
        try
        {
            FileInputStream fi = new FileInputStream(file);
            int count = fi.read(data);
            if (count == -1)
                LOG.warn("Not load the whole file!");
            fi.close();
        } catch (IOException e)
        {
            data = null;
        }
        return data;
    }

    /**
     * 把数据写入文件。
     *
     * @param fileName String
     * @param data     byte
     * @return int
     */
    public static int saveDataToFile(String fileName, byte[] data, int offset, int length)
    {
        try
        {
            FileOutputStream fo = new FileOutputStream(fileName);
            fo.write(data, offset, length);
            fo.close();
        } catch (IOException e)
        {
            throw new HanException(e);
        }
        return 1;
    }

    public static byte[] buildInputData(InputStream stream) throws IOException
    {
        ByteArrayOutputStream bo = null;

        try
        {
            bo = new ByteArrayOutputStream(stream.available());
            byte[] b = new byte[1024];

            int n;

            while ((n = stream.read(b)) > 0)
            {
                bo.write(b, 0, n);
            }

            return bo.toByteArray();
        } finally
        {
            if (bo != null)
                bo.close();
        }
    }

    public static String loadTextFromStream(InputStream stream, String encoding) throws IOException
    {
        byte[] data = buildInputData(stream);

        return new String(data, encoding);
    }

    public static String loadTextFromFile(String fileName, String encoding)
    {
        StringBuilder sb = new StringBuilder();

        BufferedReader reader = null;
        try (InputStream istream = new FileInputStream(fileName))
        {
            if (encoding == null)
                reader = new BufferedReader(new InputStreamReader(istream));
            else
                try
                {
                    reader = new BufferedReader(new InputStreamReader(istream, encoding));
                } catch (UnsupportedEncodingException e)
                {
                    LOG.error("Unsupported encodeing ({})", encoding);
                    return null;
                }
            do
            {
                String line;
                line = reader.readLine();
                if (line == null)
                    break;
                sb.append(line);
            }
            while (true);
        } catch (IOException e)
        {
            LOG.error("loadTextFromFile throw exception '{}'", e.getMessage());
            return null;
        } finally
        {
            if (reader != null)
                try
                {
                    reader.close();
                } catch (IOException e)
                {
                    LOG.warn("close read failed ({})", e.getMessage());
                }
        }
        return sb.toString();
    }

    public static int saveTextToFile(String fileName, String text)
    {
        byte[] data = null;

        try
        {
            data = text.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e)
        {
            LOG.error("unsupported encoding UTF-8");
            return -1;
        }
        return saveDataToFile(fileName, data, 0, data.length);
    }

    public static int saveTextToFile(String fileName, String text, String encoding)
    {
        byte[] data = null;

        if (HanHelper.isEmpty(text) || HanHelper.isEmpty(fileName))
        {
            LOG.error("bad parameter. text is {}, fileName is {}", text, fileName);
            return -1;
        }

        try
        {
            data = text.getBytes(encoding);
        } catch (UnsupportedEncodingException e)
        {
            LOG.error("unsupported encoding '{}'", encoding);
            return -1;
        }
        return saveDataToFile(fileName, data, 0, data.length);
    }

    /**
     * 复制文件
     *
     * @param srcFileName 源文件
     * @param dstFileName 目标文件
     * @param isOverwrite 如果目标文件已经存在，是否覆盖目标文件
     * @throws IOException 异常
     */
    public static void FileCopy(String srcFileName, String dstFileName, boolean isOverwrite) throws IOException
    {
        // 判断目标文件是否存在
        File dstFile = new File(dstFileName);
        if (dstFile.exists() && !isOverwrite)
        {
            String message = String.format("target file [%s] has exist.", dstFileName);
            throw new IOException(message);
        }
        // 打开目标文件
        FileInputStream fis = new FileInputStream(srcFileName);

        // 打开源文件
        File srcFile = new File(srcFileName);
        FileOutputStream fos = new FileOutputStream(dstFile);

        // 复制数据
        HanHelper.write(fis, fos);

        // 关闭输入输出流
        fis.close();
        fos.close();

        // 设置目标文件属性
        if (!dstFile.setLastModified(srcFile.lastModified()))
        {
            LOG.error("setLastModified exception");
        }
        if (!srcFile.canWrite())
            if (!dstFile.setReadOnly())
            {
                LOG.error("setReadOnly exception");
            }
    }

    /**
     * 移动文件
     *
     * @param srcFileName 源文件
     * @param dstFileName 目标文件
     * @param isOverwrite 如果目标文件已经存在，是否覆盖目标文件
     * @throws IOException ...
     */
    public static void FileMove(String srcFileName, String dstFileName, boolean isOverwrite) throws IOException
    {
        // 复制文件
        FileCopy(srcFileName, dstFileName, isOverwrite);

        // 删除源文件
        new File(srcFileName).delete();
    }

    /**
     * 创建目录，包括全路径
     *
     * @param dirName 目录
     */
    public static void mkdirs(String dirName)
    {
        File f = new File(dirName);
        if (!f.exists())
            f.mkdirs();
    }

    /**
     * 获取文件的基础名称，如c:\path\filename.ext的基础名是filename
     *
     * @param fileName 文件名称
     * @return 文件的基础名称
     */
    public static String getFileBaseName(String fileName)
    {
        // 获取纯粹的文件名
        File f = new File(fileName);
        fileName = f.getName();

        // 基础名
        int pos = fileName.lastIndexOf('.');
        return pos < 0 ? fileName : fileName.substring(0, pos);
    }

    /**
     * 获取文件的扩展名，如c:\path\filename.ext的扩展名是ext
     *
     * @param fileName 文件名称
     * @return 文件扩展名
     */
    public static String getFileExtName(String fileName)
    {
        // 获取纯粹的文件名
        File f = new File(fileName);
        fileName = f.getName();

        // 扩展名
        int pos = fileName.lastIndexOf('.');
        return pos < 0 ? "" : fileName.substring(pos + 1);
    }

    public static boolean delete(String fileName)
    {
        try
        {
            return new File(fileName).delete();
        } catch (Exception e)
        {
            LOG.error("delete file({}) failed", fileName);
            return false;
        }
    }

    public static boolean exists(String fileName)
    {
        return new File(fileName).exists();
    }
}
