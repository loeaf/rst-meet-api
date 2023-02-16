package com.loeaf.rstmeet.service.impl;

import com.loeaf.common.misc.ServiceImpl;
import com.loeaf.rstmeet.dto.RstMeetFile;
import com.loeaf.rstmeet.model.Restaurant;
import com.loeaf.rstmeet.repository.RestaurantRepository;
import com.loeaf.rstmeet.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;

import javax.annotation.PostConstruct;
import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RestaurantServiceImpl
        extends ServiceImpl<RestaurantRepository, Restaurant, String>
        implements RestaurantService {
    private final RestaurantRepository jpaRepo;

    @PostConstruct
    private void init() {
        super.set(jpaRepo, new Restaurant());
    }

    @Override
    @Transactional
    public void registBulkByCSV() throws FileNotFoundException {
        List<RstMeetFile> rstMeetFileList = this.readCSVByClassPath();
        rstMeetFileList.forEach(p -> {
            System.out.println(p.toString());
            var rst = new Restaurant();
            rst.setId(UUID.randomUUID().toString());
            rst.setRestaurantNumber(p.getRstMeetFileNumber());
            rst.setName(p.getRestaurant());
            rst.setRoadAddress(p.getKoreanRoadAddress());
            rst.setJibunAddress(p.getKoreanJibunAddress());
            rst.setEnglishAddress(p.getEnglishAddress());
            rst.setMiniAddress(p.getArea());
            rst.setLatitude(new BigDecimal(p.getLat()).longValue());
            rst.setLongitude(new BigDecimal(p.getLog()).longValue());
            rst.setGeoInfo(null);
            rst.setRegDate(new Date());
            rst.setPhoneNumber(p.getPhoneNumber());
            rst.setHoliday(p.getHoliday());
            rst.setRepresentativeMenu(p.getRepresentativeMenu());
            this.jpaRepo.save(rst);
        });
    }

    private List<RstMeetFile> readCSVByClassPath() throws FileNotFoundException {
        List<RstMeetFile> rstMeetFileList = new ArrayList<>();
        BufferedReader br = null;
//        File file = ResourceUtils.getFile("classpath:/static/file/sejong_rst_meet.csv");
        File file = ResourceUtils.getFile("src/main/resources/static/file/sejong_rst_meet.csv");
        if (file.exists()) {
            System.out.println("file exists");
        } else {
            System.out.println("file not exists");
        }
        InputStream targetStream = new DataInputStream(new FileInputStream(file));
        try{
            br = new BufferedReader(new InputStreamReader(targetStream, "UTF-8"));
            //Charset.forName("UTF-8");
            String line = "";
            int idx = 0;
            while((line = br.readLine()) != null){
                if(idx == 0){
                    idx++;
                    continue;
                }
                //CSV 1행을 저장하는 리스트
                List<String> tmpList = new ArrayList<String>();
                String array[] = line.split(",");
                var rstMeetFile = new RstMeetFile();
                rstMeetFile.setRstMeetFileNumber(Integer.parseInt(array[0]));
                rstMeetFile.setCategory(array[1]);
                rstMeetFile.setArea(array[2]);
                rstMeetFile.setRestaurant(array[3]);
                rstMeetFile.setRepresentativeMenu(array[4]);
                rstMeetFile.setHoliday(array[5]);
                rstMeetFile.setPhoneNumber(array[6]);
                rstMeetFile.setKoreanRoadAddress(array[7]);
                rstMeetFile.setKoreanJibunAddress(array[8]);
                rstMeetFile.setEnglishAddress(array[9]);
                rstMeetFile.setSpecAddr(array[10]);
                rstMeetFile.setLog(array[11]);
                rstMeetFile.setLat(array[12]);
                System.out.println(rstMeetFile.toString());
                rstMeetFileList.add(rstMeetFile);
            }
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }finally{
            try{
                if(br != null){
                    br.close();
                }
            }catch(IOException e){
                e.printStackTrace();
            }
        }
        return rstMeetFileList;
    }
}
