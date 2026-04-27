package com.AirBnb.projects.airbnb_app.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Setter
@Getter
@Table(name = "hotel")
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private  String name;

    private  String city;

    @Column(columnDefinition = "TEXT[]")
    private  String[] photos;

    @Column(columnDefinition = "TEXT[]")
    private  String[] amenities;

    @CreationTimestamp
    private LocalDateTime createAt;

    @UpdateTimestamp
    private  LocalDateTime updatedAt;

    @Embedded
    private HotelContactInfo contactInfo;

    @Column(nullable = false)
    private Boolean active = false;

//    @OneToMany(mappedBy = "hotel", fetch = FetchType.LAZY)
//    private List<Room> room;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private  User owner;

    @OneToMany(mappedBy = "hotel")
    @JsonIgnore
    private List<Room> rooms;

}


