package squaring.vitrox.buxpj.Network;

import rx.Observable;
import squaring.vitrox.buxpj.Model.SocketMessage;

/**
 * Created by miguelgomez on 10/21/16.
 */

public interface socketConnectionListener {

    void receivedmessage(SocketMessage message);

    void onError(String error);

}
