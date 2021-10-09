package com.dao;

import org.apache.ibatis.session.SqlSession;

import com.dto.MemberDTO;

public class MemberDAO {

   public int memberAdd(SqlSession session,MemberDTO dto) {
	   System.out.println(dto);
	   int n = session.insert("MemberMapper.memberAdd", dto);
	   return n;
   }
}
