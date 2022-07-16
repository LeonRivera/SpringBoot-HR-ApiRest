package com.leonrv.hrapirest.repositories;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.*;
import org.springframework.stereotype.*;

@NoRepositoryBean
public interface IGenericRepository<T> extends JpaRepository<T, Long> {}
