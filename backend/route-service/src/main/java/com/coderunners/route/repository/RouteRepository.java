package com.coderunners.route.repository;

import com.coderunners.route.entity.Route;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RouteRepository extends JpaRepository<Route, Integer> {
}
