package com.ldav.siriusemailservice.factory;

public interface SimpleFactory<Entity, Dto, Form> {
    public Dto getDTOFrom(Entity entity);
    public Entity getNewEntityFrom(Form form);
}