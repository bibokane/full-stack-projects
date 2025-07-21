package com.bibokane.jobportal.repository;

import com.bibokane.jobportal.entity.JobPostActivity;
import com.bibokane.jobportal.entity.JobSeekerApply;
import com.bibokane.jobportal.entity.JobSeekerProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobSeekerApplyRepository extends JpaRepository<JobSeekerApply, Integer> {

    List<JobSeekerApply> findByUserId(JobSeekerProfile userId);

    List<JobSeekerApply> findByJob(JobPostActivity job);
}
