package com.loeaf.rstmeet.service.impl;

import com.loeaf.common.misc.ServiceImpl;
import com.loeaf.rstmeet.dto.MediaFile;
import com.loeaf.rstmeet.model.Media;
import com.loeaf.rstmeet.model.Restaurant;
import com.loeaf.rstmeet.repository.MediaRepository;
import com.loeaf.rstmeet.repository.RestaurantRepository;
import com.loeaf.rstmeet.service.MediaService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import javax.annotation.PostConstruct;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MediaServiceImpl
        extends ServiceImpl<MediaRepository, Media, String>
        implements MediaService {
    private final MediaRepository jpaRepo;
    @Autowired
    private final RestaurantRepository restaurantRepository;

    @PostConstruct
    private void init() {
        super.set(jpaRepo, new Media());
    }

    @Override
    public void registBulkByCSV() throws FileNotFoundException {
        // parse csv file
        List<MediaFile> menuFiles = this.readCSVByClassPath();
        // save to db
        menuFiles.forEach(p -> {
            var review = new Media();
            Optional<Restaurant> restaurantObj = restaurantRepository.findById(p.getRestaurantUuid());
            if(restaurantObj.isEmpty()) {
                return;
            }
            review.setId(UUID.randomUUID().toString());
            review.setName(p.getName());
            review.setPath(p.getPath());
            review.setRestaurant(restaurantObj.get());
            this.jpaRepo.save(review);
        });
    }


    private List<MediaFile> readCSVByClassPath() throws FileNotFoundException {
        List<MediaFile> mediaFileList = new ArrayList<>();
        BufferedReader br = null;
        File file = ResourceUtils.getFile("src/main/resources/static/file/sejong_rst_meet_media.csv");
        if (file.exists()) {
            System.out.println("file exists");
        } else {
            System.out.println("file not exists");
        }
        InputStream targetStream = new DataInputStream(new FileInputStream(file));
        try{
            br = new BufferedReader(new InputStreamReader(targetStream, "UTF-8"));
            String line = "";
            int i = 0;
            while((line = br.readLine()) != null) {
                i++;
                if (i == 1) {
                    continue;
                }
                //CSV 1행을 저장하는 리스트
                List<String> tmpList = new ArrayList<String>();
                String array[] = line.split(",");
                var rstMeetMediaFile = new MediaFile();
                // 식당번호
                rstMeetMediaFile.setMediaId(Integer.parseInt(array[0]));
                rstMeetMediaFile.setName(array[1]);
                rstMeetMediaFile.setPath(array[2]);
                rstMeetMediaFile.setRestaurantUuid(array[3]);
                System.out.println(rstMeetMediaFile.toString());
                mediaFileList.add(rstMeetMediaFile);
            }
        } catch(FileNotFoundException e) {
            e.printStackTrace();
        } catch(IOException e) {
            e.printStackTrace();
        } finally {
            try{
                if(br != null){
                    br.close();
                }
            }catch(IOException e){
                e.printStackTrace();
            }
        }
        return mediaFileList;
    }
}
