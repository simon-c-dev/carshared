package com.coderunners.route.controller;


import com.coderunners.route.entity.Route;
import com.coderunners.route.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/routes")
public class RouteController {

    @Autowired
    private RouteService routeService;

    @PostMapping
    public ResponseEntity<Route> addRoute(@RequestBody Route route) {
        Route newRoute = routeService.addRoute(route);
        return ResponseEntity.ok(newRoute);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Route> updateRoute(@PathVariable Integer id, @RequestBody Route route) {
        Route updatedRoute = routeService.updateRoute(id, route);
        return ResponseEntity.ok(updatedRoute);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRoute(@PathVariable Integer id) {
        routeService.deleteRoute(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Route> getRouteById(@PathVariable Integer id) {
        Optional<Route> route = routeService.getRouteById(id);
        return route.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Route>> getAllRoutes() {
        List<Route> routes = routeService.getAllRoutes();
        return ResponseEntity.ok(routes);
    }

    @PostMapping("/reserve/{id}")
    public ResponseEntity<Route> reserveRoute(@PathVariable Integer id) {
        Route reservedRoute = routeService.reserveRoute(id);
        if (reservedRoute != null) {
            return ResponseEntity.ok(reservedRoute);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
