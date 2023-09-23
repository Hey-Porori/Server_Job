package porori.backend.job.domain.post.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import porori.backend.job.domain.post.constant.DOW;

import javax.persistence.*;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class WorkDay {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "work_day_id")
    private Long workDayId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_post_id")
    private JobPost jobPost;

    @Enumerated(EnumType.STRING)
    private DOW dow;
    /**
     * 연관관계 매핑
     */
    public void setJobPost(JobPost jobPost){
        this.jobPost=jobPost;
        jobPost.getWorkDays().add(this);
    }

    public WorkDay(DOW dow){
        this.dow=dow;
    }
}
