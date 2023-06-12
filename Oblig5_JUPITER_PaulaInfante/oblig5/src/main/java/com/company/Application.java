package com.company;

import com.company.controller.PlanetController;
import com.company.repository.UniverseCSVRepository;
import com.company.repository.UniverseJSONRepository;
import io.javalin.Javalin;
import io.javalin.plugin.rendering.vue.VueComponent;

public class Application {
    public static void main(String[] args) {
        // Setter kontrolleren
        PlanetController planetController = new PlanetController(new UniverseJSONRepository("planets_100"));

        Javalin app = Javalin.create().start();
        app.config.enableWebjars();
        app.before("/", ctx -> ctx.redirect("/planet-system"));

        /*
        Sider
         */
        app.get("/planet-system", new VueComponent("planet-system-overview"));
        app.get("/planet-system/:planet-system-id", new VueComponent("planet-system-detail"));

        // 2.3
        app.get("/planet-system/:planet-system-id/planets/create", new VueComponent("planet-create"));

        app.get("/planet-system/:planet-system-id/planets/:planet-id", new VueComponent("planet-detail"));

        // 2.3
        app.get("/planet-system/:planet-system-id/planets/:planet-id/update", new VueComponent("planet-update"));

        /*
        API
         */
        app.get("/api/planet-system", ctx -> planetController.getPlanetSystems(ctx));
        app.get("/api/planet-system/:planet-system-id", ctx -> planetController.getPlanetSystem(ctx));
        app.get("/api/planet-system/:planet-system-id/planets", ctx -> planetController.getPlanets(ctx));
        app.get("/api/planet-system/:planet-system-id/planets/:planet-id", ctx -> planetController.getPlanet(ctx));

        // 2.3
        app.get("/api/planet-system/:planet-system-id/planets/:planet-id/delete", ctx -> planetController.deletePlanet(ctx));
        app.post("/api/planet-system/:planet-system-id/planets/create", ctx -> planetController.createPlanet(ctx));
        app.post("/api/planet-system/:planet-system-id/planets/:planet-id/update", ctx -> planetController.updatePlanet(ctx));
    }
}
