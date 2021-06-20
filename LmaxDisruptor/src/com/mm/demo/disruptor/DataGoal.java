package com.mm.demo.disruptor;

import com.lmax.disruptor.EventFactory;

class DataGoal {

    private int idPlayer;
    private int idTeam;
    private int time;

    public void Set(int idPlayer,int idTeam, int time){
        this.idPlayer=idPlayer;
        this.idTeam=idTeam;
        this.time = time;
    }
    public int GetIdPlayer(){
        return idPlayer;
    }
    public int GtIdTeam(){
        return idTeam;
    }

    public final static EventFactory<DataGoal> EVENT_FACTORY = new EventFactory<DataGoal>() {
        public DataGoal newInstance() {
            return new DataGoal();
        }
    };
}
