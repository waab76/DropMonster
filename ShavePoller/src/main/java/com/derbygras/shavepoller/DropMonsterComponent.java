package com.derbygras.shavepoller;

import javax.inject.Singleton;

import com.derbygras.shavepoller.bizlogic.DropMonsterImpl;
import com.derbygras.shavepoller.module.CommonModule;

import dagger.Component;

@Singleton
@Component(modules = { CommonModule.class })
public interface DropMonsterComponent {

    DropMonsterImpl dropMonster();
}
