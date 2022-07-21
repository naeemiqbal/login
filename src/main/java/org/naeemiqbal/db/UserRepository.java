/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.naeemiqbal.db;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Naeem Iqbal
 */
public interface UserRepository extends CrudRepository<Users, Long> {
    List<Users> findByUserName(String userName);
}
