package com.game.whac_a_mole.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
@Slf4j
public class MemberRepository {
    private Map<String, Integer> memberDB;
    //TODO: Think of using DB(maybe redis)
    //      before then, use in-memory hash-table
    public MemberRepository() {
        this.memberDB = new InMemoryDB().getMemberDB();
    }

    public boolean saveMember(String memberId){
        log.info("savemember  : {}", memberId);
        if(this.memberDB.containsKey(memberId)){ //already saved member
            return false;
        }else{ // not yet saved member
            this.memberDB.put(memberId, 1);
            return true;
        }
    }

    public boolean deleteMember(String memberId){
        if(this.memberDB.containsKey(memberId)){ //already saved member
            this.memberDB.remove(memberId);
            return true;
        }else{ // not yet saved member
            return false;
        }

    }
}
