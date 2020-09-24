package com.ghj.common.fastdfs;

import lombok.Data;

/**
 * @author gehj
 * @date 2019/6/25 14:02
 */
@Data
public class FastDFSFile {
    private String name;
    private byte[] content;
    private String ext;
    private String md5;
    private String author;
    private String height;
}
