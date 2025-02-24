package io.github.ExperionPlanet.tools.item.tool_lambdas;

public class ExistsLambdas {
    boolean onPostHit, onPostMine;
    boolean onUse, onUseBlock;

    public ExistsLambdas() {
        this.onPostHit = false;
        this.onPostMine = false;
        this.onUse = false;
        this.onUseBlock = false;
    }

    public ExistsLambdas PostHit() {
        this.onPostHit = true;
        return this;
    }

    public ExistsLambdas PostMine() {
        this.onPostMine = true;
        return this;
    }

    public ExistsLambdas Use() {
        this.onUse = true;
        return this;
    }

    public ExistsLambdas UseBlock() {
        this.onUseBlock = true;
        return this;
    }
}
