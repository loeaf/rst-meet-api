package com.loeaf.rstmeet.service.impl;

import com.loeaf.common.misc.ServiceImpl;
import com.loeaf.rstmeet.dto.MenuFile;
import com.loeaf.rstmeet.model.CmmnCode;
import com.loeaf.rstmeet.model.Menu;
import com.loeaf.rstmeet.model.Restaurant;
import com.loeaf.rstmeet.repository.CmmnCodeRepository;
import com.loeaf.rstmeet.repository.MenuRepository;
import com.loeaf.rstmeet.repository.RestaurantRepository;
import com.loeaf.rstmeet.service.MenuService;
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
public class MenuServiceImpl
        extends ServiceImpl<MenuRepository, Menu, String>
        implements MenuService {
    private final MenuRepository jpaRepo;
    @Autowired
    private final CmmnCodeRepository cmmnCodeRepository;
    @Autowired
    private RestaurantRepository restaurantRepository;

    @PostConstruct
    private void init() {
        super.set(jpaRepo, new Menu());
    }

    @Override
    public void registBulkByCSV() throws FileNotFoundException {
        // parse csv file
        List<MenuFile> menuFiles = this.readCSVByClassPath();
        // save to db
        menuFiles.forEach(p -> {
            var menu = new Menu();
            Restaurant restaurantObj = restaurantRepository.findByRestaurantNumber(p.getRestaurantId());
            CmmnCode menuObj = cmmnCodeRepository.findByCodeName(p.getMenuType());
            menu.setId(UUID.randomUUID().toString());
            menu.setMenuAmount(p.getMenuAmount());
            menu.setName(p.getName());
            menu.setRestaurant(restaurantObj);
            menu.setMenuType(menuObj);
            menu.setPhotoUrl(p.getPhotoUrl());
            menu.setPrice(p.getPrice());
            menu.setIsMain(p.getIsMain());
            menu.setRegDate(new Date());
            this.jpaRepo.save(menu);
        });
    }

    private List<MenuFile> readCSVByClassPath() throws FileNotFoundException {
        List<MenuFile> rstMeetFileList = new ArrayList<>();
        BufferedReader br = null;
        File file = ResourceUtils.getFile("src/main/resources/static/file/sejong_rst_meet_menu.csv");
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
                if(i == 0){
                    i++;
                    continue;
                }
                //CSV 1행을 저장하는 리스트
                List<String> tmpList = new ArrayList<String>();
                String array[] = line.split(",");
                var rstMeetFile = new MenuFile();
                // 식당번호
                rstMeetFile.setRestaurantId(Integer.parseInt(array[0]));
                // 메뉴이름
                rstMeetFile.setName(array[1]);
                // 메뉴량
                rstMeetFile.setMenuAmount(array[2]);
                // 메뉴가격
                rstMeetFile.setPrice(array[3]);
                // 메뉴사진
                rstMeetFile.setPhotoUrl(array[4]);
                // 메뉴설명
                rstMeetFile.setDescription(array[5]);
                // 메뉴종류
                rstMeetFile.setMenuType(array[6]);
                // 대표메뉴여부
                rstMeetFile.setIsMain(array[7]);
                System.out.println(rstMeetFile.toString());
                rstMeetFileList.add(rstMeetFile);
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
        return rstMeetFileList;
    }
}
