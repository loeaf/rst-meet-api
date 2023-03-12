package com.loeaf.rstmeet.service.impl;

import com.loeaf.common.misc.ServiceImpl;
import com.loeaf.rstmeet.dto.RstMeetFile;
import com.loeaf.rstmeet.dto.params.RestaurantParam;
import com.loeaf.rstmeet.mapper.RestaurantMapper;
import com.loeaf.rstmeet.model.Restaurant;
import com.loeaf.rstmeet.model.RestaurantDto;
import com.loeaf.rstmeet.repository.RestaurantRepository;
import com.loeaf.rstmeet.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;

import javax.annotation.PostConstruct;
import java.io.*;
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
    @Autowired
    private final RestaurantMapper restaurantMapper;

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
            List<Restaurant> restaurantList =  this.jpaRepo.findByRoadAddressAndName(p.getKoreanRoadAddress(), p.getRestaurant());
            if(restaurantList.size() > 0) {
                System.out.println("Already Exist" + p.toString());
                return;
            }
            var rst = new Restaurant();
            rst.setId(UUID.randomUUID().toString());
            rst.setRestaurantNumber(p.getRstMeetFileNumber());
            rst.setName(p.getRestaurant());
            rst.setRoadAddress(p.getKoreanRoadAddress());
            rst.setJibunAddress(p.getKoreanJibunAddress());
            rst.setEnglishAddress(p.getEnglishAddress());
            rst.setMiniAddress(p.getArea());
            rst.setLatitude(Double.parseDouble(p.getLat()));
            rst.setLongitude(Double.parseDouble(p.getLog()));
            rst.setGeoInfo(null);
            rst.setRegDate(new Date());
            rst.setPhoneNumber(p.getPhoneNumber());
            rst.setHoliday(p.getHoliday());
            rst.setRefinedGeoLocation(0);
            rst.setRepresentativeMenu(p.getRepresentativeMenu());
            this.jpaRepo.save(rst);
        });
    }

    @Override
    public List<RestaurantDto> findRestaurant(RestaurantParam restaurant) {
        List<RestaurantDto> restaurantList = restaurantMapper.findRestaurant(restaurant);
        return restaurantList;
    }

    @Override
    public List<Restaurant> findRestaurantByRoadAddress(String roadAddress, String name) {
        List<Restaurant> restaurantList = jpaRepo.findByRoadAddressAndName(roadAddress, name);
        return null;
    }

    @Override
    public List<RestaurantDto> findNearestRestaurant(RestaurantParam o) {
        List<RestaurantDto> restaurantList = restaurantMapper.findNearestRestaurant(o);
        return restaurantList;
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
                String array[] = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
                System.out.println(line);
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
                if (array.length > 12) {
                    rstMeetFile.setLog(array[11]);
                    rstMeetFile.setLat(array[12]);
                } else {
                    rstMeetFile.setLog("0");
                    rstMeetFile.setLat("0");
                }
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
