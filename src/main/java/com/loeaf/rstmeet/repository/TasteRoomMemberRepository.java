package com.loeaf.rstmeet.repository;

import com.loeaf.rstmeet.model.TasteRoomMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TasteRoomMemberRepository extends JpaRepository<TasteRoomMember, String> {
}