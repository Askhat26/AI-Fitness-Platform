package fitness.activity_service.repository;



import fitness.activity_service.model.Activity;
import fitness.activity_service.model.ActivityType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long> {

    List<Activity> findByUserId(String userId);

    List<Activity> findByUserIdAndType(String userId, ActivityType type);

    List<Activity> findByUserIdOrderByStartTimeDesc(String userId);

    List<Activity> findByUserIdAndStartTimeBetween(String userId, LocalDateTime startDate, LocalDateTime endDate);

    @Query("SELECT a FROM Activity a WHERE a.userId = :userId AND a.startTime >= :startTime ORDER BY a.startTime DESC")
    List<Activity> findRecentActivitiesByUser(@Param("userId") String userId, @Param("startTime") LocalDateTime startTime);

    @Query("SELECT COUNT(a) FROM Activity a WHERE a.userId = :userId AND a.type = :type")
    Long countByUserIdAndType(@Param("userId") String userId, @Param("type") ActivityType type);

    @Query("SELECT SUM(a.caloriesBurned) FROM Activity a WHERE a.userId = :userId AND a.startTime >= :startDate")
    Integer getTotalCaloriesBurnedSince(@Param("userId") String userId, @Param("startDate") LocalDateTime startDate);
}
