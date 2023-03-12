package com.loeaf.rstmeet.service.impl;

import com.loeaf.common.misc.ServiceImpl;
import com.loeaf.rstmeet.dto.ReviewFile;
import com.loeaf.rstmeet.model.ReView;
import com.loeaf.rstmeet.model.Restaurant;
import com.loeaf.rstmeet.repository.ReViewRepository;
import com.loeaf.rstmeet.repository.RestaurantRepository;
import com.loeaf.rstmeet.service.ReViewService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import javax.annotation.PostConstruct;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReViewServiceImpl
        extends ServiceImpl<ReViewRepository, ReView, String>
        implements ReViewService {
    private final ReViewRepository jpaRepo;
    @Autowired
    private RestaurantRepository restaurantRepository;
    @PostConstruct
    private void init() {
        super.set(jpaRepo, new ReView());
    }

    @Override
    public void registBulkByCSV() throws FileNotFoundException {
        // parse csv file
        List<ReviewFile> menuFiles = this.readCSVByClassPath();
        // save to db
        menuFiles.forEach(p -> {
            var review = new ReView();
            Restaurant restaurantObj = restaurantRepository.findByRestaurantNumber(1);
            review.setId(UUID.randomUUID().toString());
            review.setContent(p.getContent());
            review.setIsMain(p.getIsMain());
            review.setRegDate(new Date());
            review.setRestaurant(restaurantObj);
            this.jpaRepo.save(review);
        });
    }

    private List<ReviewFile> readCSVByClassPath() throws FileNotFoundException {
        List<ReviewFile> reviewFileList = new ArrayList<>();
        BufferedReader br = null;
        File file = ResourceUtils.getFile("src/main/resources/static/file/sejong_rst_meet_review.csv");
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
            while((line = br.readLine()) != null){
                i++;
                if (i == 1) {
                    continue;
                }
                //CSV 1행을 저장하는 리스트
                List<String> tmpList = new ArrayList<String>();
                String array[] = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
                var rstMeetFile = new ReviewFile();
                // 식당번호
                rstMeetFile.setRestaurantId(Integer.parseInt(array[0]));
                // 내용
                rstMeetFile.setContent(array[1]);
                // 대표리뷰여부
                rstMeetFile.setIsMain(array[2]);
                System.out.println(rstMeetFile.toString());
                reviewFileList.add(rstMeetFile);
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
        return reviewFileList;
    }
}
