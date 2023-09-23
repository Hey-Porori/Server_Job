package porori.backend.job.domain.post.domain.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import porori.backend.job.domain.post.constant.JobType;
import porori.backend.job.domain.post.domain.entity.Job;
import porori.backend.job.domain.post.domain.entity.JobPost;
import porori.backend.job.domain.post.domain.entity.WorkDay;
import porori.backend.job.domain.post.domain.repository.JobRepository;
import porori.backend.job.domain.post.domain.repository.PostRepository;
import porori.backend.job.domain.post.domain.repository.WorkDayRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SavePostService {
    private final PostRepository postRepository;
    private final WorkDayRepository workDayRepository;
    private final JobRepository jobRepository;

    public void savePost(JobPost jobPost, List<WorkDay> workDays, List<Job> jobs) {
        this.postRepository.save(jobPost);
        this.workDayRepository.saveAll(workDays);
        this.jobRepository.saveAll(jobs);
    }
}
