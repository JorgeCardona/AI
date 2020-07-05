from enum import Enum
from configuration.config import ALL_DATA, PREDICTION, CLASIFICATOR

class StoreData(Enum):

    SAVE_ALL_DATA     = ALL_DATA
    SAVE_PREDICTION   = PREDICTION
    SAVE_CLASIFICATOR = CLASIFICATOR