### 공통 관계 사용 예시
- @OneToMany => 1(파일):M(파일내용) 관계 매핑.
- @ManyToOne => M(파일내용):1(파일) 관계 매핑.
- 1개의 파일안에는 여러개의 파일 내용이 존재한다.
- 여러개의 파일 내용은 1개의 파일을 원본으로 가진다.
```java
@Entity
@Table(name="file_info")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FileInfo extends Domain {
    @JsonManagedReference
    @OneToMany(mappedBy = "fileInfo", fetch=FetchType.LAZY, cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<SampleCsv> sampleCsvDetail = new ArrayList<>();

    public void addCityInfo(SampleCsv CPReportDetail) {
        if(CPReportDetail.getFileInfo() != this)
            CPReportDetail.setFileInfo(this);
        this.sampleCsvDetail.add(CPReportDetail);
    }
    public void addCityInfos(List<SampleCsv> CPReportDetail) {
        CPReportDetail.forEach(p -> {
            if(p.getFileInfo() != this)
                p.setFileInfo(this);
            this.sampleCsvDetail.add(p);
        });
    }
}

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class SampleCsv extends Domain {
    private String col0;
    private String col1;

    /**
     * COMMENT '파일 id'"
     */
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="file_id")
    private FileInfo fileInfo;
}
```

- M(사용자):M(역할) 관계 매핑
- 사용자는 여러 역할을 가질 수 있고 여러 역할은 사용자에게 배정 될 수 있다.
- M:M 관계를 만들기 위한 매핑 테이블이 생성됨
```java
@Entity
@Table(name = "user")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User extends Domain {
    @ManyToMany
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;
}

@Entity
@Table(name = "role")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Role extends Domain {
    @Column(length = 10, nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    @BizField
    private Authority authority;
}
```