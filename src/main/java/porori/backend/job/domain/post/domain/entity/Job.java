package porori.backend.job.domain.post.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import porori.backend.job.domain.post.constant.JobType;

import javax.persistence.*;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "job_id")
    private Long jobId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_post_id")
    private JobPost jobPost;

    @Enumerated(EnumType.STRING)
    private JobType jobType;

    /**
     * 연관관계 매핑
     */
    public void setJobPost(JobPost jobPost){
        this.jobPost=jobPost;
        jobPost.getJobs().add(this);
    }

    public Job(JobType jobType){
        this.jobType=jobType;
    }
}

