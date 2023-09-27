package porori.backend.job.domain.post.application.dto.response;

import lombok.Getter;
import porori.backend.job.domain.post.constant.DOW;
import porori.backend.job.domain.post.constant.JobType;

import java.util.ArrayList;
import java.util.List;

@Getter
public class PostBlocks {
    private PostContent postContent;
    private List<JobType> jobTypes = new ArrayList<>();
    private List<DOW> dows = new ArrayList<>();

    public PostBlocks(PostContent postContent, List<JobType> jobTypes, List<DOW> dows) {
        this.postContent = postContent;
        this.dows = dows;
        this.jobTypes = jobTypes;
    }
}