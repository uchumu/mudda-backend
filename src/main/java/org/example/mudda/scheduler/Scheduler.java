package org.example.mudda.scheduler;

import lombok.RequiredArgsConstructor;
import org.example.mudda.entity.Capsule;
import org.example.mudda.repository.CapsuleRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

@Component
@RequiredArgsConstructor
public class Scheduler {

    private final CapsuleRepository capsuleRepository;

    @Scheduled(fixedDelay = 5000)
    @Transactional
    public void schedulerCapsuleOpened() {

        long currentTime = Instant.now().getEpochSecond(); // 현재 시간(초 단위)

        System.out.println("currentTime = " + currentTime);

        List<Capsule> capsuleList = capsuleRepository.findByGoalTimeLessThanEqualAndStatus(currentTime, "digged");

        for (Capsule capsule : capsuleList) {
            capsule.updateStatus("opened");
            capsuleRepository.save(capsule);
        }


    }
}
