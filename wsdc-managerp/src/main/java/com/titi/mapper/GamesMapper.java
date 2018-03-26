package com.titi.mapper;

import com.titi.entity.GameTypeFormMap;
import com.titi.entity.GamesFormMap;
import com.titi.mapper.base.BaseMapper;

import java.util.List;


/**
 * 游戏 dao
 * @author lixiaoyu
 *
 */
public interface GamesMapper extends BaseMapper{

    //分页查询游戏分类信息
    List<GameTypeFormMap> findGameTypePage(GameTypeFormMap gameTypeFormMap);

    //分页查询游戏信息
    List<GamesFormMap> findGamePage(GamesFormMap gamesFormMap);

}
