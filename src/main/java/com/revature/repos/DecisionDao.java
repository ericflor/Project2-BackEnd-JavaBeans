package com.revature.repos;

import com.revature.models.Decisions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DecisionDao extends JpaRepository<Decisions, Integer> {

    List<Decisions> findDistinctByRoundIdAndUserId(int roundId, int groupId);

//    @Query(value="SELECT imdb_id FROM (SELECT imdb_id, count(imdb_id) AS total FROM decisions JOIN user_tbl ON decisions.user_id = user_tbl.id WHERE user_tbl.group_id = ?1 AND decisions.round_id = ?2 AND choice GROUP BY imdb_id ORDER BY total DESC LIMIT 1) AS results;", nativeQuery = true)
@Query(value="SELECT imdb_id FROM (\n" +
        "\tSELECT imdb_id, count(imdb_id) AS total \n" +
        "\tFROM decisions JOIN user_tbl ON decisions.user_id = user_tbl.id\n" +
        "\tWHERE user_tbl.group_id = ?1 \n" +
        "\t\tAND choice\n" +
        "\t\tAND decisions.round_id = (\n" +
        "\t\t\tSELECT max(decisions.round_id) \n" +
        "\t\t\tFROM decisions JOIN user_tbl ON decisions.user_id = user_tbl.id \n" +
        "\t\t\tWHERE user_tbl.group_id = ?1) \n" +
        "\tGROUP BY imdb_id\n" +
        "\tORDER BY total DESC \n" +
        "\tLIMIT 1\n" +
        ") AS results;", nativeQuery = true)
    String getWinner(int groupId);

    int countByUserGroupId(int groupId);
    Decisions findByImdbIdAndRoundIdAndUserId(String ImdbId, int roundId, int userId);
}
