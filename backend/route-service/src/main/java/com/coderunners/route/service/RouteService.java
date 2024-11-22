package com.coderunners.route.service;

import com.coderunners.route.entity.Route;
import com.coderunners.route.repository.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RouteService {
    @Autowired
    private RouteRepository routeRepository;

    public Route addRoute(Route route) {
        return routeRepository.save(route);
    }

    public Route updateRoute(Integer id, Route route) {
        route.setId(id);
        return routeRepository.save(route);
    }

    public void deleteRoute(Integer id) {
        routeRepository.deleteById(id);
    }

    public Optional<Route> getRouteById(Integer id) {
        return routeRepository.findById(id);
    }

    public List<Route> getAllRoutes() {
        return routeRepository.findAll();
    }

    public Route reserveRoute(Integer id) {
        Optional<Route> route = routeRepository.findById(id);
        if (route.isPresent()) {
            Route r = route.get();
            r.setReserved(true);
            return routeRepository.save(r);
        }
        return null;
    }
}
