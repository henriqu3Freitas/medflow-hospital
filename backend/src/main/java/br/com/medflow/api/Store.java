package br.com.medflow.api;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public final class Store {
    private Store() {}
    public static final Map<UUID, Object> pacientes = new ConcurrentHashMap<>();
    public static final Map<UUID, Object> profissionais = new ConcurrentHashMap<>();
    public static final Map<UUID, Object> consultas = new ConcurrentHashMap<>();
    public static final Map<UUID, Object> internacoes = new ConcurrentHashMap<>();
    public static final Map<UUID, Object> quartos = new ConcurrentHashMap<>();
    public static final Map<UUID, Object> procedimentos = new ConcurrentHashMap<>();
    public static final Map<UUID, Object> emergencias = new ConcurrentHashMap<>();
}
