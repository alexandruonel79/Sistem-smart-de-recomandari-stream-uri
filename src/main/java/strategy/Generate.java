package strategy;

public interface Generate<T> {
    T getObject(int id,long noOfStreams,int streamerId,long length,long dateAdded,String name,int streamGenre);
}
