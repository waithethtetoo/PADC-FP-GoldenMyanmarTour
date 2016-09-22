package com.padc.goldenmyanmartour.data.Models;

import com.padc.goldenmyanmartour.data.agent.DestinationDataAgent;
import com.padc.goldenmyanmartour.data.agent.RetrofitDataAgent;

/**
 * Created by Lenovo on 9/21/2016.
 */
public abstract class BaseModel {
    private static final int INIT_DATA_AGENT_RETROFIT = 1;
    protected DestinationDataAgent dataAgent;

    public BaseModel() {
        initDataAgent(INIT_DATA_AGENT_RETROFIT);
    }

    private void initDataAgent(int type) {
        if (type == INIT_DATA_AGENT_RETROFIT) {
            dataAgent = RetrofitDataAgent.getInstance();
        }
    }
}
