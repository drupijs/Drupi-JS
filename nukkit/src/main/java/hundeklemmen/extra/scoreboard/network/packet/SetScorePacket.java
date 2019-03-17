package hundeklemmen.extra.scoreboard.network.packet;

import cn.nukkit.network.protocol.DataPacket;
import lombok.*;

import java.util.List;

@Data
public class SetScorePacket extends DataPacket {

    public static final byte NETWORK_ID = 0x6c;

    private byte type;
    private List<ScoreEntry> entries;

    @Override
    public byte pid() {
        return NETWORK_ID;
    }

    @Override
    public void decode() {
        //Ignore
    }

    @Override
    public void encode() {
        this.reset();
        this.putByte( this.type );
        this.putUnsignedVarInt( this.entries.size() );

        for ( ScoreEntry entry : this.entries ) {
            this.putVarLong( entry.scoreId );
            this.putString( entry.objective );
            this.putLInt( entry.score );

            this.putByte( entry.entityType );

            if(entry.getEntityType() == 1 && entry.getEntityType() == 2){
                this.putUnsignedVarLong( entry.entityId );

            } else if(entry.getEntityType() == 3){
                this.putString( entry.fakeEntity );
            }
        }
    }

    @Getter
    @ToString
    @AllArgsConstructor
    @RequiredArgsConstructor
    public static class ScoreEntry {
        private final long scoreId;
        private final String objective;
        private final int score;

        // Add entity type
        private byte entityType;
        private String fakeEntity;
        private long entityId;
    }
}
