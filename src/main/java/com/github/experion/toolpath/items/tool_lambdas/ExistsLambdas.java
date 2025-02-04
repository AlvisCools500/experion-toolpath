package com.github.experion.toolpath.items.tool_lambdas;

public class ExistsLambdas {
    boolean onPostHit, onPostMine;
    boolean onUse, onUseBlock;
    boolean editEffeciency, editDamage, editTexture;
    boolean onThundered;

    public ExistsLambdas() {
        this.onPostHit = false;
        this.onPostMine = false;
        this.onUse = false;
        this.onUseBlock = false;
        this.onThundered = false;
        this.editEffeciency = false;
        this.editDamage = false;
        this.editTexture = false;

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

    public ExistsLambdas EditEffeciency() {
        this.editEffeciency = true;
        return this;
    }

    public ExistsLambdas EditDamage() {
        this.editDamage = true;
        return this;
    }

    public ExistsLambdas Thundered() {
        this.onThundered = true;
        return this;
    }

    public ExistsLambdas EditRender() {
        this.editTexture = true;
        return this;
    }
}
