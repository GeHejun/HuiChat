package com.ghj.rest.service.impl;

import com.ghj.rest.service.FileService;
import com.github.tobato.fastdfs.domain.StorePath;
import org.springframework.stereotype.Service;

/**
 * @author gehj
 * @date 2019/6/2718:20
 */
@Service
public class FileServiceImpl implements FileService {

//
//    @Resource
//    private FastFileStorageClient storageClient;


//    /**
//     * 上传文件
//     * @param file 文件对象
//     * @return 文件访问地址
//     * @throws IOException
//     */
//    @Override
//    public String uploadFile(MultipartFile file)  {
//        StorePath storePath ;
//        try {
//            storePath = storageClient.uploadFile(file.getInputStream(),file.getSize(), FilenameUtils.getExtension(file.getOriginalFilename()),null);
//        } catch (IOException e) {
//            throw new FileException();
//        }
//        return getResAccessUrl(storePath);
//    }
//
//    /**
//     * 将一段字符串生成一个文件上传
//     * @param content 文件内容
//     * @param fileExtension
//     * @return
//     */
//    @Override
//    public String uploadFile(String content, String fileExtension) {
//        byte[] buff = content.getBytes(Charset.forName("UTF-8"));
//        ByteArrayInputStream stream = new ByteArrayInputStream(buff);
//        StorePath storePath = storageClient.uploadFile(stream,buff.length, fileExtension,null);
//        return getResAccessUrl(storePath);
//    }

    private String getResAccessUrl(StorePath storePath) {
        String fileUrl = storePath.getFullPath();
        return fileUrl;
    }

//    /**
//     * 删除文件
//     * @param fileUrl 文件访问地址
//     * @return
//     */
//    @Override
//    public void deleteFile(String fileUrl) {
//        if (StringUtils.isEmpty(fileUrl)) {
//            return;
//        }
//        try {
//            StorePath storePath = StorePath.praseFromUrl(fileUrl);
//            storageClient.deleteFile(storePath.getGroup(), storePath.getPath());
//        } catch (FdfsUnsupportStorePathException e) {
//            throw  new RuntimeException();
//        }
//    }

}
