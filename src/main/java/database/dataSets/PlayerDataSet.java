package database.dataSets;

import org.jetbrains.annotations.NotNull;

import javax.persistence.*;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;


/**
 * Created by pavel on 24.11.15.
 */
@Entity
@Table(name="Player")
public class PlayerDataSet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long playerId;

    @Column()
    private int playedGames;

    @Column()
    private int wonGames;

}
