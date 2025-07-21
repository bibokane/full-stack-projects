package com.bibokane.jobportal.repository;

import com.bibokane.jobportal.entity.JobPostActivity;
import com.bibokane.jobportal.entity.JobSeekerProfile;
import com.bibokane.jobportal.entity.JobSeekerSave;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobSeekerSaveRepository extends JpaRepository<JobSeekerSave, Integer> {
    public List<JobSeekerSave> findByUserId(JobSeekerProfile userAccountId);

    List<JobSeekerSave> findByJob(JobPostActivity job);
}
