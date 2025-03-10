package org.zerock.b01.dto.upload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UploadResultDTO {
    private String uuid;
    private String fileName;
    private boolean img;

    public String getLink() {
        if(img)
            return "s_" + uuid + "_" + fileName; // 이미지인 경우 링크는 썸네일파일 정보를 전달
        else
            return uuid + "_" + fileName;       // 일반 파일인 경우 링크는 썸네일 파일 정보를 전달
    }
}
