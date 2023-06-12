package com.company;

import com.company.controller.PlanetController;
import com.company.repository.UniverseRepository;
import io.javalin.Javalin;
import io.javalin.plugin.rendering.vue.VueComponent;

public class Application {
    public static void main(String[] args) {
        Javalin app = Javalin.create().start();
        app.config.enableWebjars();
        app.before("/", ctx -> ctx.redirect("/planet-system"));

        app.get("/planet-system", new VueComponent("planet-system-overview"));
        app.get("/planet-system/:planet-system-id", new VueComponent("planet-system-detail"));
        app.get("/planet-system/:planet-system-id/planets/:planet-id", new VueComponent("planet-detail"));

        PlanetController planetController = new PlanetController(new UniverseRepository());

        app.get("/api/planet-system", ctx -> planetController.getPlanetSystems(ctx));
        app.get("/api/planet-system/:planet-system-id", ctx -> planetController.getPlanetSystem(ctx));
        app.get("/api/planet-system/:planet-system-id/planets", ctx -> planetController.getPlanets(ctx));
        app.get("/api/planet-system/:planet-system-id/planets/:planet-id", ctx -> planetController.getPlanet(ctx));
    }
}
